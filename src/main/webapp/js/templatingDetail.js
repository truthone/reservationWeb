
window.addEventListener("load",function(){
	const displayInfoId = 
	document.querySelector("meta[name='displayInfoId']").getAttribute("content");
	productDetailAjax(displayInfoId);
});

function productDetailAjax(displayInfoId){
	const url = "api/products/"+displayInfoId;
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load",function(){
        let json = JSON.parse(this.responseText);
		let productImageInfo = json.productImageInfo;
		let displayInfo = json.displayInfo;
		let displayImageInfo = json.displayImageInfo;
		let commentInfo = json.commentInfo;
		let averageScore = json.averageScore;
		let commentCount = json.commentCount;
		
        templatingProductImage(productImageInfo,displayInfo);
        templatingDisplayInfo(displayInfo,displayImageInfo);
        templatingComments(displayInfoId,commentInfo,averageScore,commentCount);
        
        slideInfinityProductImage();
	});
	
	oReq.open("GET",url);
	oReq.send();
	
}

function slideInfinityProductImage(){
	const imageList = document.querySelector(".visual_img");
	let firstCloneItem = imageList.lastElementChild;
	let firstItem = imageList.children[1];
	let lastItem = imageList.children[imageList.children.length-2];
	let lastCloneItem = imageList.firstElementChild;
	
	firstCloneItem.id = "FIRSTCLONE";
	firstItem.id = "FIRST";
	lastItem.id = "LAST";
	lastCloneItem.id = "LASTCLONE";
	
	const itemWidth = $(".container_visual").css("width").replace("px","");
	let nowImageIndex = 1;
	let totalIndex = imageList.children.length;
	let lastIndex = imageList.children.length-2;
	let firstIndex = 1;
	
	$("#nowIndex").text(nowImageIndex);
	imageList.style.transform = "translateX(" + (firstIndex*(-itemWidth)) + "px)";
	
	$(".prev").click(function(){
		imageList.style.transitionDuration = "0.2s";
		
		if(imageList.children[nowImageIndex].id == "FIRST"){
			nowImageIndex--;
			imageList.style.transform = "translateX(" + (nowImageIndex*itemWidth) + "px)";
		}else{
			nowImageIndex--;
			$("#nowIndex").text(nowImageIndex);
			imageList.style.transitionDuration = "0.2s";
			imageList.style.transform = "translateX(" + (nowImageIndex*(-itemWidth)) + "px)";
		}
	});
	
	$(".nxt").click(function(){
		imageList.style.transitionDuration = "0.2s";
		
		if(imageList.children[nowImageIndex].id == "LAST"){
			nowImageIndex++;
			imageList.style.transform = "translateX(" + (nowImageIndex*(-itemWidth)) + "px)";
		}else{
			nowImageIndex++;
			$("#nowIndex").text(nowImageIndex);
			imageList.style.transitionDuration = "0.2s";
			imageList.style.transform = "translateX(" + (nowImageIndex*(-itemWidth)) + "px)";
		}
	});

	imageList.addEventListener("transitionend", function(){
		if(imageList.children[nowImageIndex].id == "FIRSTCLONE"){
			nowImageIndex = firstIndex;			
		}
		else if(imageList.children[nowImageIndex].id == "LASTCLONE"){
			nowImageIndex = lastIndex;
		}
		
		$("#nowIndex").text(nowImageIndex);
		imageList.style.transitionDuration = "initial";
		imageList.style.transform = "translateX(" + (nowImageIndex*(-itemWidth)) + "px)";
	});
}

function slideProductImage(){
	const imageList = document.querySelector(".visual_img");
	let firstItem = imageList. firstElementChild;
	let LastItem = imageList.lastElementChild;
	firstItem.id = "FIRST";
	LastItem.id = "LAST";
	const itemWidth = $(".container_visual").css("width").replace("px","");
	let nowImageIndex = 0;
	let totalIndex = imageList.length;
	const prevButton = document.querySelector(".prev");
	const nxtButton = document.querySelector(".nxt");
	$("#nowIndex").text(nowImageIndex+1);
	
	$(".prev").click(function(){
		if(imageList.children[nowImageIndex].id != "FIRST"){
			nowImageIndex--;
			$("#nowIndex").text(nowImageIndex+1);
			
			imageList.style.transitionDuration = "0.5s";
			imageList.style.transform = "translateX(" + (nowImageIndex*itemWidth) + "px)";
			
			if(imageList.children[nowImageIndex].id === 'FIRST'){
				$(".prev").find("i").addClass('off');
				$(".nxt").find("i").removeClass('off');
			}
		}
	});
	
	$(".nxt").click(function(){
		if(imageList.children[nowImageIndex].id != "LAST"){
			nowImageIndex++;
			$("#nowIndex").text(nowImageIndex+1);
			
			imageList.style.transitionDuration = "0.5s";
			imageList.style.transform = "translateX(" + (nowImageIndex*(-itemWidth)) + "px)";
			
			if(imageList.children[nowImageIndex].id === 'LAST'){
				$(".nxt").find("i").addClass('off');
				$(".prev").find("i").removeClass('off');
			}
		}
	});
}

function templatingProductImage(productImageInfo,displayInfo){
	const productImageList = document.querySelector(".visual_img");
	const productImageItemTemplate = document.querySelector("#productImageItem").innerHTML;
	let bindProductImageItemTemplate= Handlebars.compile(productImageItemTemplate);
	let resultHTML="";
	const LIMIT = 2;
	let count = 1;
	
	if( 1 < productImageInfo.length){
		
		let typeFilteredProductImageInfo = productImageInfo.filter(function(imageInfo){
			if(imageInfo.type == "et" && count < LIMIT){
				count++;
				return imageInfo;
			}
		    return imageInfo.type == "ma";
		})
		
		resultHTML += bindProductImageItemTemplate(typeFilteredProductImageInfo[typeFilteredProductImageInfo.length-1]);
		
		typeFilteredProductImageInfo.forEach(function(typeFilteredProductImageInfo,index){
			resultHTML += bindProductImageItemTemplate(typeFilteredProductImageInfo);
		}); 
		
		resultHTML += bindProductImageItemTemplate(typeFilteredProductImageInfo[0]);
	}else{
			resultHTML += bindProductImageItemTemplate(productImageInfo[productImageInfo.length-1]);
			productImageInfo.forEach(function(productImageInfo, index){
			resultHTML += bindProductImageItemTemplate(productImageInfo);
			resultHTML += bindProductImageItemTemplate(productImageInfo[0]);
	    });
	}
	productImageList.insertAdjacentHTML("beforeend",resultHTML);
	
	$("#totalIndex").text(count);
	$(".visual_txt_tit").children("span").html(displayInfo[0].productDescription);
	
	if(count < 2){
		$(".prev").hide();
		$(".nxt").hide();
	} 
}

function templatingDisplayInfo(displayInfo,displayImageInfo){
	const sectionInfoTab = document.querySelector("#section_info_tab").innerHTML;
	let resultHTML = "";
	let bindTemplate = Handlebars.compile(sectionInfoTab);
	
	displayInfo.forEach(function(displayInfo,index){
		resultHTML += bindTemplate(displayInfo);
	});
	$(".section_review_list").after(resultHTML);
	
	const description  = displayInfo[0].productContent;
	$(".dsc").html(description);	
	
	const map = displayImageInfo[0].saveFileName;
	document.querySelector(".store_map.img_thumb").src=' ../' + map;
	clickSectionInfoTab();
}

function  templatingComments(displayInfoId,commentInfo,averageScore,commentCount){
	const shortReviewItem = document.querySelector("#list_short_review_item").innerHTML;
	const noImageShortReviewItem =document.querySelector("#list_short_review_item_no_img").innerHTML;
	let resultHTML = "";
	const Limit = 3; 
	let i = 0;
	const totalScore = 5.0;
	let bindReviewItemTemplate = Handlebars.compile(shortReviewItem);
	let bindNoImageReviewItemTemplate = Handlebars.compile(noImageShortReviewItem);
	
	if(commentInfo != ""){
		 commentInfo.forEach(function(commentInfo,index){
			 if(i < Limit){
				 if(commentInfo.saveFileName !== null ) resultHTML += bindReviewItemTemplate(commentInfo);
				 else resultHTML += bindNoImageReviewItemTemplate(commentInfo);
			 	}
			   i++;
			 });
		}
		
	document.querySelector(".list_short_review").insertAdjacentHTML("beforeend",resultHTML);
	
	let averagePercent = (averageScore / totalScore ) * 100;
	
	$("#averageScore").text(averageScore);
	$(".green").text(commentCount+"건");
	$(".btn_review_more").attr("href",displayInfoId+"/review");
	$(".graph_value").width(averagePercent+"%");
}

function clickSectionInfoTab(){
	document.querySelector(".section_info_tab").addEventListener("click",function(evt){
		let nowActive = document.getElementsByClassName("anchor active")
		
		if(evt.target.tagName === "SPAN"){
			  
			  nowActive[0].className = "anchor";
			  
			  let clickStatus = evt.target.parentNode; 
			  clickStatus.className = "anchor active";			  
			  
		}else if(evt.target.tagName === "A"){
			  nowActive[0].className = "anchor";			 
			  let clickStatus = evt.target; 
			  clickStatus.className = "anchor active";
		}		
		  if(nowActive[0].parentNode.className == "item _detail"){
			  $(".detail_area_wrap").removeClass("hide");
			  $(".detail_location").addClass("hide");
			  
		  }else if (nowActive[0].parentNode.className == "item _path"){
			  $(".detail_area_wrap").addClass("hide");
			  $(".detail_location").removeClass("hide");
		  }
	});	
}

$(document).ready(function(){
	$(".bk_more._open").click(function(){
		$(".bk_more._open").css("display","none");
		$(".bk_more._close").css("display","block");
		$(".store_details").removeClass("close3");
	});
	$(".bk_more._close").click(function(){
		$(".bk_more._open").css("display","block");
		$(".bk_more._close").css("display","none");
		$(".store_details").addClass("close3");
	});
});



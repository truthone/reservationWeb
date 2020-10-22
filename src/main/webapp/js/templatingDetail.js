
window.addEventListener("load",function(){
	const displayInfoId = 
	document.querySelector("meta[name='displayInfoId']").getAttribute("content");
	productDetailAjax(displayInfoId);
});

function productDetailAjax(displayInfoId){
	const url = "/reservation/api/products/"+displayInfoId;
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load",function(){
        let json = JSON.parse(this.responseText);
		let productImageInfo = json.productImageInfo;
		let displayInfo = json.displayInfo;
		let displayImageInfo = json.displayImageInfo;
		let commentInfo = json.commentInfo;
		let averageScore = json.averageScore;
		let commentCount = json.commentCount;
		
		const productImageList = document.querySelector(".visual_img");
		
		
		
		imageList.templatingProductImage(productImageList,productImageInfo,displayInfo,4);
        templatingDisplayInfo(displayInfo,displayImageInfo);
        templatingComments(displayInfoId,commentInfo,averageScore,commentCount,3);

        
	});
	
	oReq.open("GET",url);
	oReq.send();
}

templating = {
		
		bindingTemplate : function(bindingPlace, bindingData){
			let template = Handlebars.compile(bindingPlace);
			return template(bindingData);
		},
		
		insertResultHTML : function(insertList,resultHTML){
			insertList.insertAdjacentHTML("beforeend",resultHTML);
		}
}

imageList = {
		
		 filterImageInfoListByType: function(productImageInfo, typeString){
			let typeFilteredInfoList = 
				productImageInfo.filter(function(imageInfo){
					return imageInfo.type == typeString;
				});
			return typeFilteredInfoList;
		},
			
		 orderImageSequence: function(typeMainImageInfoList, typeEtcImageInfoList,LIMIT){
			let orderedList = new Array();
			let etcImagecount = 0;
			let mainImageCount = 0;

			typeMainImageInfoList.forEach(function(index){
				orderedList.push(index);
				mainImageCount++;
			});
			
			if(mainImageCount < LIMIT){
				typeEtcImageInfoList.forEach(function(index){
					if(etcImagecount < LIMIT - mainImageCount){
						orderedList.push(index);
						etcImagecount++;
					}
				});
			}
			return orderedList;
		},
		
		templatingProductImage: function(productImageList,productImageInfo,displayInfo,LIMIT){
			const productImageItemTemplate = document.querySelector("#productImageItem").innerHTML;
			let resultHTML="";
			let typeMainImageInfoList = this.filterImageInfoListByType(productImageInfo, "ma");
			let typeEtcImageInfoList = this.filterImageInfoListByType(productImageInfo, "et");
			let orderedAndFilteredList =  this.orderImageSequence(typeMainImageInfoList,typeEtcImageInfoList,LIMIT);
			const total =  orderedAndFilteredList.length;
			
			const listLength = orderedAndFilteredList.length;
			const firstImage = orderedAndFilteredList[0]; 
			const lastImage = orderedAndFilteredList[listLength-1];
			
			resultHTML += templating.bindingTemplate(productImageItemTemplate, lastImage);
			orderedAndFilteredList.forEach(function(image){
				resultHTML += templating.bindingTemplate(productImageItemTemplate, image);
			});
			resultHTML += templating.bindingTemplate(productImageItemTemplate, firstImage);
			
			templating.insertResultHTML(productImageList,resultHTML);
			
			
			$("#totalIndex").text(total);
			
			if(total < 2){
				$(".prev").hide();
				$(".nxt").hide();
			} 
			slideInfinityProductImage();
		}
}


function templatingDisplayInfo(displayInfo,displayImageInfo){
	const sectionInfoTab = document.querySelector("#section_info_tab").innerHTML;
	let resultHTML = "";
	
	displayInfo.forEach(function(displayInfo,index){
		resultHTML += templating.bindingTemplate(sectionInfoTab, displayInfo);
	});
	$(".section_review_list").after(resultHTML);
	
	const description  = displayInfo[0].productContent;
	$(".dsc").html(description);	
	
	const productTitle = displayInfo[0].productDescription;
	$(".visual_txt_tit").children("span").html(productTitle);
	
	const map = displayImageInfo[0].saveFileName;
	document.querySelector(".store_map.img_thumb").src=' ../' + map;
	clickSectionInfoTab();
}


function templatingComments(displayInfoId,commentInfo,averageScore,commentCount,LIMIT){
	const shortReviewItem = document.querySelector("#list_short_review_item").innerHTML;
	const noImageShortReviewItem =document.querySelector("#list_short_review_item_no_img").innerHTML;
	let shortReviewList = document.querySelector(".list_short_review");
	const TOTAL_SCORE = 5.0;
	
	let averagePercent = (averageScore / TOTAL_SCORE ) * 100;
	let resultHTML = "";
	
function filterImageInfoListByType(productImageInfo, typeString){
	let typeFilteredInfoList = 
		productImageInfo.filter(function(imageInfo){
			return imageInfo.type == typeString;
		});
	return typeFilteredInfoList;
}
	
function orderImageSequence(typeMainImageInfoList, typeEtcImageInfoList, LIMIT){
	let orderedList = new Array();
	let etcImagecount = 0;
	let mainImageCount = 0;


			typeMainImageInfoList.forEach(function(index){
				orderedList.push(index);
				mainImageCount++;
			});
			
			if(mainImageCount < LIMIT){
				typeEtcImageInfoList.forEach(function(index){
					if(etcImagecount < LIMIT - mainImageCount){
						orderedList.push(index);
						etcImagecount++;
					}
				});
			}
			return orderedList;
		},
		
		templatingProductImage: function(productImageList,productImageInfo,displayInfo,LIMIT){
			const productImageItemTemplate = document.querySelector("#productImageItem").innerHTML;
			let resultHTML="";
			let typeMainImageInfoList = this.filterImageInfoListByType(productImageInfo, "ma");
			let typeEtcImageInfoList = this.filterImageInfoListByType(productImageInfo, "et");
			let orderedAndFilteredList =  this.orderImageSequence(typeMainImageInfoList,typeEtcImageInfoList,LIMIT);
			const total =  orderedAndFilteredList.length;
			
			const listLength = orderedAndFilteredList.length;
			const firstImage = orderedAndFilteredList[0]; 
			const lastImage = orderedAndFilteredList[listLength-1];
			
			resultHTML += templating.bindingTemplate(productImageItemTemplate, lastImage);
			orderedAndFilteredList.forEach(function(image){
				resultHTML += templating.bindingTemplate(productImageItemTemplate, image);
			});
			resultHTML += templating.bindingTemplate(productImageItemTemplate, firstImage);
			
			templating.insertResultHTML(productImageList,resultHTML);
			
			
			$("#totalIndex").text(total);
			
			if(total < 2){
				$(".prev").hide();
				$(".nxt").hide();
			} 
			slideInfinityProductImage();
		}
}


function templatingDisplayInfo(displayInfo,displayImageInfo){
	const sectionInfoTab = document.querySelector("#section_info_tab").innerHTML;
	let resultHTML = "";
	
	displayInfo.forEach(function(displayInfo,index){
		resultHTML += templating.bindingTemplate(sectionInfoTab, displayInfo);
	});
	$(".section_review_list").after(resultHTML);
	
	const description  = displayInfo[0].productContent;
	$(".dsc").html(description);	
	
	const productTitle = displayInfo[0].productDescription;
	$(".visual_txt_tit").children("span").html(productTitle);
	
	const map = displayImageInfo[0].saveFileName;
	document.querySelector(".store_map.img_thumb").src=' ../' + map;
	clickSectionInfoTab();
}


function templatingComments(displayInfoId,commentInfo,averageScore,commentCount,LIMIT){
	const shortReviewItem = document.querySelector("#list_short_review_item").innerHTML;
	const noImageShortReviewItem =document.querySelector("#list_short_review_item_no_img").innerHTML;
	let shortReviewList = document.querySelector(".list_short_review");
	const TOTAL_SCORE = 5.0;
	
	let averagePercent = (averageScore / TOTAL_SCORE ) * 100;
	let resultHTML = "";
	

	if(commentInfo != ""){
		for(let i = 0; i < LIMIT ; i++){
			 if(commentInfo.saveFileName != null ){
				 resultHTML += templating.bindingTemplate(shortReviewItem, commentInfo[i]);
			 }else{
				 resultHTML += templating.bindingTemplate(noImageShortReviewItem, commentInfo[i]);
			 }
		 }
	}
	
	templating.insertResultHTML(shortReviewList, resultHTML);
		
	$("#averageScore").text(averageScore);
	$(".green").text(commentCount + "건");
	$(".btn_review_more").attr("href",displayInfoId + "/review");
	$(".graph_value").width(averagePercent + "%");
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

	imageList.addEventListener("transitionstart", function(){
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
	let firstItem = imageList.firstElementChild;
	let lastItem = imageList.lastElementChild;
	firstItem.id = "FIRST";
	lastItem.id = "LAST";
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

function clickSectionInfoTab(){
	document.querySelector(".section_info_tab").addEventListener("click",function(evt){
		let nowActive = document.getElementsByClassName("anchor active");
		
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




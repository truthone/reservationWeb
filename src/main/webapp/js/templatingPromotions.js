document.addEventListener("DOMContentloaded", function(){
	ajaxPromtoions();
});

function ajaxPromtoions(){
	let oReq = new XMLHttpRequest();
	const url = "api/promotions";
	
	oReq.addEventListener("load",function(){
		const json = JSON.parse(this.responseText);
		let promotionList = json.promotionList;
		let length = promotionList.length;
		
		templatingPromotions(promotionList);
		slideSetting();
		
	});
	oReq.open("GET",url);
	oReq.send();
}

function templatingPromotions(promotionList) {

	let promotionHTML = document.querySelector("#promotionItem").innerHTML;
	let promotionImageList = document.querySelector(".visual_img");
	const size = promotionList.length;
	let lastCloneHTML ="";
	
	lastCloneHTML += promotionHTML
	    .replace(/{productImageUrl}/g, promotionList[size-1].productImageUrl)
	    .replace(/{sequence}/g, "LAST CLONE");
	
	promotionImageList.insertAdjacentHTML("afterbegin", lastCloneHTML);
	  
	for (i = 0; i < size; ++i){
		let resultHTML="";
		resultHTML += promotionHTML
            .replace(/{productImageUrl}/g, promotionList[i].productImageUrl)
            .replace(/{sequence}/g,i+1);
                
		promotionImageList.insertAdjacentHTML("beforeend", resultHTML);
	}
	
	if(i == size){
		let firstCloneHTML = "";
		firstCloneHTML += promotionHTML
		    .replace(/{productImageUrl}/g, promotionList[1].productImageUrl)
		    .replace(/{sequence}/g, "FIRST CLONE");
		
		promotionImageList.insertAdjacentHTML("beforeend", firstCloneHTML); 
	} 
	
}

const imageList = document.querySelector(".visual_img");
const container = document.querySelector(".container_visual");
let nowImageSequence = 1;
let itemWidth;
let moveLeft;
const delayTime = 2000;	
	
function slideSetting(){
		
    	let firstItem = imageList.children[1];
		let flagTime;
		firstItem.id = "FIRST";
		itemWidth = $(".visual_img").find("#FIRST").css("width").replace("px","");
		moveLeft = -itemWidth;
		
		imageList.style.transform = "translateX(" + (nowImageSequence*moveLeft) + "px)";
		
		slide(flagTime);
}
function slide(flagTime){
    let nowTime = new Date().getTime();
    
    
    if(flagTime == undefined) flagTime = nowTime + delayTime;
    if(nowTime > flagTime){   
    	const imageListSize = imageList.childElementCount;
		let lastItem = imageList.children[imageListSize-2];
		lastItem.id = "LAST";
		++nowImageSequence;
    	imageList.style.transitionDuration = "0.5s";
    	imageList.style.transform = "translateX(" + (nowImageSequence * moveLeft) + "px)";
    	
    	flagTime = nowTime + delayTime;
	   
    }
    requestAnimationFrame(function(){slide(flagTime)});
}

imageList.addEventListener("transitionend", function(){
		if(imageList.children[nowImageSequence].id === 'LAST'){
			nowImageSequence = 0;
			imageList.style.transitionDuration = "initial";
			imageList.style.transform = "translateX(" + nowImageSequence * moveLeft + "px)";
		}
});
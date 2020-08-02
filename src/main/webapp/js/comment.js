window.addEventListener("DOMContentLoaded",function(){
	imageExtensionCheck();
	focusOnReviewTextarea();
	
});

window.addEventListener("load",function(){
	clickStarRating();
	clickReviewSubmitBtn();
});

function commentInfoAjax(){
	const reservationInfoId = document.querySelector("meta[name='reservationInfoId']").getAttribute("content");
	let oReq = new XMLHttpRequest();
	const url = "/reservation/api/comment"+reservationInfoId;
	
	oReq.addEventListener("DOMContentloaded",function(){
		let json = JSON.parse(this.responseText);
		
	});
}

function imageExtensionCheck(){
	const uploadImage = document.querySelector("#reviewImageFileOpenInput");
	uploadImage.addEventListener("change",(evt)=>{
		const image = evt.target.files[0];
		if(!validImageType(image)){
			console.warn("invaild image file type");
			return;
		}
		templatingThumbImage(image)
	});
}

function validImageType(image){
	const result = (['image/jpeg',
					'image/png',
					'image/jpg'].indexOf(image.type) > -1);
	return result;
}

function templatingThumbImage(image){
	const thumbImage = document.querySelector(".item_thumb");
	thumbImage.src = window.URL.createObjectURL(image);
	thumbImage.parentElement.style.display = "table-cell";
	clickDeleteThumbImgBtn(thumbImage);
}

function clickDeleteThumbImgBtn(thumbImage){
	$(".spr_book.ico_del").click(function(){
		thumbImage.src = ""
		thumbImage.parentElement.style.display = "none";});
}

function focusOnReviewTextarea(){
	const reviewWriteInfo = document.querySelector(".review_write_info");
	reviewWriteInfo.addEventListener("click",function(evt){
		evt.currentTarget.style.display = 'none';
		const reviewTextarea = document.querySelector(".review_textarea");
		reviewTextarea.focus();
		countTextByteForGuide(reviewTextarea);
	});
}

function countTextByteForGuide(textarea){
	const nowByteGuide = document.querySelector(".guide_review > span");
	
	textarea.addEventListener("keyup",evt=>{
		const target = evt.currentTarget;
		let nowTextLength = target.value.length;
		nowByteGuide.innerText = nowTextLength;
		return nowTextLength;
	});
	
}


function clickStarRating(){
	
	const rating = document.querySelector(".rating");
	
	rating.addEventListener("click",function(evt){
		if(evt.target.className === 'rating_rdo'){
			const clickedRatingValue = evt.target.value;
			let ratingBtns = document.getElementsByClassName("rating_rdo");
			
			for(let i = 0; i < ratingBtns.length; i++){
				ratingBtns[i].checked = false;
			}
			
			for(let i = 0; i < clickedRatingValue; i++){
				ratingBtns[i].checked = true;
			}
			templatingRatingScore(clickedRatingValue);
		}
	});
}

function templatingRatingScore(score){
	$("#score").text(score);
	if($("#score").text() !== 0){
		$("#score").removeClass("gray_star"); 
	}
}


function clickReviewSubmitBtn(){
	
	document.querySelector(".bk_btn").addEventListener("click",evt=>{
		debugger;
		evt.preventDefault();
		const comment = $(".review_textarea").val();
		const reservationInfoId = document.querySelector("meta[name= 'reservationInfoId']").getAttribute("content");
		const productId = document.querySelector("meta[name= 'productId']").getAttribute("content");
		const score = $("#score").text();
		const url = "/reservation/api/reservations/"+reservationInfoId+"/comment?"+"comment="+comment+"&productId="
					+productId+"&score="+score;
		$("#reviewForm").attr("action",url);
		if(checkSubmitValid(comment,productId,score)){
			
			$("#reviewForm").submit();
		}
	})
}

function checkSubmitValid(comment,productId,score){
	if(comment&& productId && score != null){
		return true;
	}
}
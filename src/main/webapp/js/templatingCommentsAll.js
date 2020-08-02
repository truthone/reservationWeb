window.addEventListener("load",function(){
	const displayInfoId = 
	document.querySelector("meta[name='displayInfoId']").getAttribute("content");
	
	productCommentAjax(displayInfoId);
	$("a.btn_back").attr("href", "../"+displayInfoId);
});

function productCommentAjax(displayInfoId){
	const url = "/reservation/api/products/"+displayInfoId+"/review";
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load",function(){
        let json = JSON.parse(this.responseText);
		let commentInfo = json.commentInfo;
		let averageScore = json.averageScore;
		let commentCount = json.commentCount;
        templatingComments(commentInfo,averageScore,commentCount);
        
	});
	
	oReq.open("GET",url);
	oReq.send();
	
}


function  templatingComments(commentInfo,averageScore,commentCount){
	const shortReviewItem = document.querySelector("#list_short_review_item").innerHTML;
	const noImageShortReviewItem =document.querySelector("#list_short_review_item_no_img").innerHTML;
	let resultHTML = "";
	let bindReviewItemTemplate = Handlebars.compile(shortReviewItem);
	let bindNoImageReviewItemTemplate = Handlebars.compile(noImageShortReviewItem);
	
	commentInfo.forEach(function(commentInfo,index){
		resultHTML += bindNoImageReviewItemTemplate(commentInfo);
	});
	
	document.querySelector(".list_short_review").insertAdjacentHTML("beforeend",resultHTML);
	
	const totalScore = 5.0;
	let averagePercent = (averageScore / totalScore ) * 100;
	
	$("#averageScore").text(averageScore);
	$(".green").text(commentCount+"건");
	$(".graph_value").width(averagePercent+"%");
}
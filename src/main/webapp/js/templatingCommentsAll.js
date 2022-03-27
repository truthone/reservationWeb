window.addEventListener("load", function () {
	const displayInfoId =
		document.querySelector("meta[name='displayInfoId']").getAttribute("content");

	productCommentAjax(displayInfoId);
	$("a.btn_back").attr("href", "../" + displayInfoId);
});

function productCommentAjax(displayInfoId) {
	const url = "/reservation/api/products/" + displayInfoId + "/review";
	var oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function () {
		let json = JSON.parse(this.responseText);
		let commentInfo = json.commentInfo;
		let averageScore = json.averageScore;
		let commentCount = json.commentCount;
		templatingComments(commentInfo, averageScore, commentCount);
	});

	oReq.open("GET", url);
	oReq.send();
}

templating = {
	bindingTemplate: function (bindingPlace, bindingData) {
		let template = Handlebars.compile(bindingPlace);
		return template(bindingData);
	},

	insertResultHTM: function (insertList, resultHTML) {
		insertList.insertAdjacentHTML("beforeend", resultHTML);
	}
}

function templatingComments(commentInfo, averageScore, commentCount) {
	const shortReviewItem = document.querySelector("#list_short_review_item").innerHTML;
	const noImageShortReviewItem = document.querySelector("#list_short_review_item_no_img").innerHTML;
	let shortReviewList = document.querySelector(".list_short_review");
	const TOTAL_SCORE = 5.0;

	let averagePercent = (averageScore / TOTAL_SCORE) * 100;
	let resultHTML = "";

	if (commentInfo !== "") {
		for (let i = 0; i < commentInfo.length; i++) {
			if (commentInfo[i].saveFileName != null) {
				resultHTML += templating.bindingTemplate(shortReviewItem, commentInfo[i]);
			} else {
				resultHTML += templating.bindingTemplate(noImageShortReviewItem, commentInfo[i]);
			}
		}
	}

	templating.insertResultHTML(shortReviewList, resultHTML);

	$("#averageScore").text(averageScore);
	$(".green").text(commentCount + "건");
	$(".graph_value").width(averagePercent + "%");
}
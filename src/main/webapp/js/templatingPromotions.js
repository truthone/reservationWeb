
function AjaxPromtoions(){
	var oReq = new XMLHttpRequest();
	var url = "api/promotions";
	
	oReq.addEventListener("load",function(){
		var json = JSON.parse(this.responseText);
		var promotionList = json.promotionList;
		var length = promotionList.length;
		
		templatingPromotion(promotionList);
		
		
	});
	oReq.open("GET",url);
	oReq.send();
}


function templatingPromotion(promotionList) {

	var promotionHTML = document.querySelector("#promotionItem").innerHTML;

    
	for (var i = 0; i < promotionList.length; i++) {

        var resultHTML="";
        
		resultHTML += promotionHTML
                .replace(/{productImageUrl}/g, promotionList[i].productImageUrl);
                 
                var Li = document.querySelector(".visual_img");
	            Li.insertAdjacentHTML("beforeend", resultHTML);
	}
	         
}

AjaxPromtoions();

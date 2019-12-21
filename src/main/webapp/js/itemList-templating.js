var pageStartList;
var categoryId= 0;
var startPageIndex =0;
var length;

function AjaxProducts(){
	
	var oReq = new XMLHttpRequest();
	var url = "api/products";
	
	oReq.addEventListener("load",function(){
		
		var json = JSON.parse(this.responseText);
		var productList = json.productList;
		
	    pageStartList = json.pageStartList;
	    length = pageStartList.length;
        startPageIndex++;
		templating(productList);
		
	});
	
	oReq.open("GET",url);
	oReq.send();
}

function searchAnchorActive(){
	var target = document.querySelector(".anchor active").parentNode;
	var categoryId = target.dataset.category;
}

document.addEventListener("DOMContentLoaded",function(){
	bubbling();
});

function bubbling (){

	var categoryList = document.querySelector("#event_tab");
	
	categoryList.addEventListener("click", function(evt){ 
		
		if(evt.target.tagName === "SPAN"){
			
		  var b = document.getElementsByClassName("anchor active")
		  b[0].className = "anchor";
		  
		  var a = evt.target.parentNode; 
		  a.className = "anchor active";
		 
		  var Li = evt.target.parentNode.parentNode;
		  
		  categoryId = Li.dataset.category;
		  clickCategoriesAjax(pageStartList,categoryId);
		  
		}else if(evt.target.tagName === "A"){
			
			  var b = document.getElementsByClassName("anchor active")
			  b[0].className = "anchor";
			  
			  var a = evt.target; 
			  a.className = "anchor active";
			  
			  var Li = evt.target.parentNode;
			  categoryId = Li.dataset.category;
			  clickCategoriesAjax(pageStartList,categoryId);

	}});
}

function clickCategoriesAjax(pageStartList,categoryId){

	removeListNode();

    moreDiv = document.querySelector(".more");	
	this.startPageIndex = 0;
	
	if(!moreDiv.firstElementChild){
		  var btnHTML = document.querySelector("#moreBtnHTML").innerHTML;

		  moreDiv.insertAdjacentHTML("beforeend",btnHTML);
		  moreBtn =  document.getElementById("moreBtn"); 
		}
	
	 moreBtn.addEventListener("click", function(){
		  ClickMoreAjax(pageStartList,categoryId);
	});
	 
	var oReq = new XMLHttpRequest();
	var url = "api/products?categoryId="+categoryId+"&start="+ 0; 
	
	oReq.addEventListener("load",function(){

		var json = JSON.parse(this.responseText);
        var productList = json.productList;
        
	    pageStartList = json.pageStartList;
	    length = pageStartList.length;
	    templating(productList);
		
	});

	oReq.open("GET",url);
	oReq.send();
	
	this.startPageIndex++; 
}


var moreBtn =  document.getElementById("moreBtn");
moreBtn.addEventListener("click", function(){
	  ClickMoreAjax(pageStartList,categoryId);
});

function ClickMoreAjax(pageStartList,categoryId){

	if(startPageIndex < length){
	
	var oReq = new XMLHttpRequest();
	var url = "api/products?categoryId="+categoryId+"&start=" + pageStartList[startPageIndex]; 

	oReq.addEventListener("load",function(){
		var json = JSON.parse(this.responseText);
		var productList = json.productList;
	    var saveFileName = json.saveFileName;
	    
		templating(productList);
		
	});
	
	oReq.open("GET",url);
	oReq.send();
    this.startPageIndex++;
    
	}
	
	if ( this.startPageIndex >= this.length){
		console.log(length);
		moreBtn.remove();
	}
}


function removeListNode(){
	var leftList = document.querySelector(".displayList_left");
	var rightList = document.querySelector(".displayList_right");
	
	while(leftList.hasChildNodes()){
	  leftList.removeChild(leftList.firstChild);
	}
	
	while(rightList.hasChildNodes()){
		rightList.removeChild(rightList.firstChild);
	}
	
    startPageIndex = 0;
}


function templating(productList) {

	var productHTML = document.querySelector("#itemList").innerHTML;
    var limit = 2;
    
	for (var i = 0; i < productList.length; i++) {

        if(i % limit == 0){
        var leftResultHTML="";
        
		leftResultHTML += productHTML
		        .replace(/{productDescription}/g,productList[i].productDescription)
		        .replace(/{productId}/g, productList[i].productId)
				.replace(/{productContent}/g, productList[i].productContent)
				.replace(/{placeName}/g, productList[i].placeName)
                .replace(/{productImageUrl}/g, productList[i].productImageUrl);
                 
                var leftLi = document.querySelector(".displayList_left");
	            leftLi.insertAdjacentHTML("beforeend", leftResultHTML);
	         
        }
        else {
        var rightResultHTML="";
        
		rightResultHTML += productHTML
               .replace(/{productDescription}/g,productList[i].productDescription)
               .replace(/{productId}/g, productList[i].productId)
		       .replace(/{productContent}/g, productList[i].productContent)
		       .replace(/{placeName}/g, productList[i].placeName)
               .replace(/{productImageUrl}/g, productList[i].productImageUrl);

               var rightLi = document.querySelector(".displayList_right");
               rightLi.insertAdjacentHTML("beforeend", rightResultHTML);
               
        }
    }
}

AjaxProducts();

                                                                                                                                             
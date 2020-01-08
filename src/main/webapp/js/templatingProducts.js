var pageStartList;
var categoryId= 0;
var startPageIndex =0;
var length;


function AjaxCategory(){
	var oReq = new XMLHttpRequest();
	var url = "api/categories";
	
	oReq.addEventListener("load",function(){
		var json = JSON.parse(this.responseText);
		var categoryList = json.categoryList;
		
		templatingCategory(categoryList);
		
	});
	oReq.open("GET",url);
	oReq.send();
}

function AjaxProducts(){
	
	var oReq = new XMLHttpRequest();
	var url = "api/products";

	
	oReq.addEventListener("load",function(){
		
		var json = JSON.parse(this.responseText);
		var productList = json.productList;
		var totalCount = json.totalCount;
		
	    pageStartList = json.pageStartList;
	    length = pageStartList.length;
        startPageIndex++;

        
        templatingProduct(productList);
        templatingCount(totalCount);
	   
	});
	
	oReq.open("GET",url);
	oReq.send();
}



function searchAnchorActive(){
	var target = document.querySelector(".anchor active").parentNode;
    categoryId = target.dataset.category;
}

document.addEventListener("DOMContentLoaded",function(){
	bubbling();
});

function bubbling (){

	let categoryListTab = document.querySelector("#event_tab");
	categoryListTab.addEventListener("click", function(evt){ 
		
		if(evt.target.tagName === "SPAN"){
			
		  var nowActive = document.getElementsByClassName("anchor active")
		  nowActive[0].className = "anchor";
		  
		  var clickStatus = evt.target.parentNode; 
		  clickStatus.className = "anchor active";
		 
		  var Li = evt.target.parentNode.parentNode;
		  
		  categoryId = Li.dataset.category;
		  startPageIndex =0;
		  clickCategoryProductAjax(startPageIndex,categoryId);
		  
		}else if(evt.target.tagName === "A"){
			
			  var nowActive = document.getElementsByClassName("anchor active")
			  nowActive[0].className = "anchor";
			  
			  var clickStatus = evt.target; 
			  clickStatus.className = "anchor active";
			  
			  var Li = evt.target.parentNode;
			  categoryId = Li.dataset.category;
			  startPageIndex =0;
			  clickCategoryProductAjax(startPageIndex,categoryId);


	}});
}




function clickCategoryProductAjax(startPageIndex,categoryId){
	removeListNode();
    moreDiv = document.querySelector(".more");	
    
	if(!moreDiv.firstElementChild){
		
		  var btnHTML = document.querySelector("#moreBtnHTML").innerHTML;
		  moreDiv.insertAdjacentHTML("beforeend",btnHTML);
		  
		  var newMoreBtn =  document.getElementById("moreBtn"); 
		  newMoreBtn.addEventListener("click", function(){
			  
			  newCategoryId = categoryId;
			  ClickMoreAjax(pageStartList,newCategoryId);
		});		 
	}
	
	var oReq = new XMLHttpRequest();
	var url = "api/products?categoryId="+categoryId+"&start="+0; 
	
	oReq.addEventListener("load",function(){
        this.startPageIndex = 0;
		var json = JSON.parse(this.responseText);
        var productList = json.productList;
        var totalCount = json.totalCount;

	    pageStartList = json.pageStartList;
	    length = pageStartList.length;
	    
	    templatingProduct(productList);
	    templatingCount(totalCount);
	    
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
	 
		templatingProduct(productList);
		
	});
	
	oReq.open("GET",url);
	oReq.send();
	
    this.startPageIndex++;
    
	}
	
	if ( this.startPageIndex >= this.length) 
		{
		    var moreBtn =  document.getElementById("moreBtn");
		    moreBtn.remove();
		    }
}


function removeListNode(){
	var leftList = document.querySelector(".displayProductList_left");
	var rightList = document.querySelector(".displayProductList_right");
	
	while(leftList.hasChildNodes()){
	  leftList.removeChild(leftList.firstChild);
	}
	
	while(rightList.hasChildNodes()){
		rightList.removeChild(rightList.firstChild);
	}
	
}

function templatingCategory(categoryList){
	var categoryHTML = document.querySelector("#categoryItem").innerHTML;
	
	for(var i = 0; i < categoryList.length; i++){
		var resultHTML = "";
		
		resultHTML += categoryHTML
				.replace(/{categoryId}/g,categoryList[i].id)
				.replace(/{categoryName}/g,categoryList[i].name);
		
		var categoryLi = document.querySelector("#event_tab");
		categoryLi.insertAdjacentHTML("beforeend",resultHTML);
	}
}

function templatingCount(totalCount){
	var countPlace = document.querySelector(".pink");
	countPlace.innerHTML = "";
	countPlace.insertAdjacentHTML("afterbegin",totalCount+"개");
}

function templatingProduct(productList) {

	var productHTML = document.querySelector("#productItem").innerHTML;
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
                 
                var leftLi = document.querySelector(".displayProductList_left");
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

               var rightLi = document.querySelector(".displayProductList_right");
               rightLi.insertAdjacentHTML("beforeend", rightResultHTML);
               
        }
    }
}


AjaxCategory();
AjaxProducts();


                                                                                                                                             
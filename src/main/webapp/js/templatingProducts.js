var pageStartList;
var categoryId= 0;
var startPageIndex =0;
var length;


document.addEventListener("DOMContentLoaded",function(){
	bubbling();
	ajaxCategory();
	ajaxProducts();
});

function ajaxCategory(){
	let oReq = new XMLHttpRequest();
	const url = "api/categories";
	
	oReq.addEventListener("load",function(){
		let json = JSON.parse(this.responseText);
		let categoryList = json.categoryList;
		
		templatingCategory(categoryList);
	});
	
	oReq.open("GET",url);
	oReq.send();
}

function ajaxProducts(){
	
	let oReq = new XMLHttpRequest();
	const url = "api/products";
	
	oReq.addEventListener("load",function(){
		
		let json = JSON.parse(this.responseText);
		let productList = json.productList;
		let totalCount = json.totalCount;
		
	    pageStartList = json.pageStartList;
	    length = pageStartList.length;
        startPageIndex++;
        templatingProduct(productList);
        templatingCount(totalCount);
	});
	
	oReq.open("GET",url);
	oReq.send();
}

function bubbling(){
	let categoryListTab = document.querySelector("#event_tab");
	categoryListTab.addEventListener("click", function(evt){ 
		
		if(evt.target.tagName === "SPAN"){
			
		  let nowActive = document.getElementsByClassName("anchor active")
		  nowActive[0].className = "anchor";
		  
		  let clickStatus = evt.target.parentNode; 
		  clickStatus.className = "anchor active";
		 
		  let Li = evt.target.parentNode.parentNode;
		  
		  categoryId = Li.dataset.category;
		  startPageIndex =0;
		  clickCategoryProductAjax(startPageIndex,categoryId);
		  
		}else if(evt.target.tagName === "A"){
			
		  let nowActive = document.getElementsByClassName("anchor active")
		  nowActive[0].className = "anchor";
		  
		  let clickStatus = evt.target; 
		  clickStatus.className = "anchor active";
		  
		  let Li = evt.target.parentNode;
		  categoryId = Li.dataset.category;
		  startPageIndex = 0;
		  
		  clickCategoryProductAjax(startPageIndex,categoryId);
	}});
}

var moreBtn =  document.getElementById("moreBtn");
moreBtn.addEventListener("click", function(){
	  clickMoreAjax(pageStartList,categoryId);
});	

function clickCategoryProductAjax(categoryId){
	removeListNode();

    if(moreBtn.style.display == "none") moreBtn.style.display = "";

		let oReq = new XMLHttpRequest();
		const url = "api/products?categoryId="+categoryId+"&start="+0; 
	
		oReq.addEventListener("load",function(){
	        this.startPageIndex = 0;
	        
			let json = JSON.parse(this.responseText);
			let productList = json.productList;
			let totalCount = json.totalCount;
	
			pageStartList = json.pageStartList;
			length = pageStartList.length;
			
			templatingProduct(productList);
			templatingCount(totalCount);	    
		});
	
	oReq.open("GET",url);
	oReq.send();
	this.startPageIndex++;
	
}

function clickMoreAjax(pageStartList,categoryId){

	if(startPageIndex < length){
	
		let oReq = new XMLHttpRequest();
		const url = "api/products?categoryId="+categoryId+"&start=" + pageStartList[startPageIndex]; 

		oReq.addEventListener("load",function(){
			 let json = JSON.parse(this.responseText);
			 let productList = json.productList;
		 
			templatingProduct(productList);
			
		});
	
		oReq.open("GET",url);
		oReq.send();
	    this.startPageIndex++;
	}
	
	if (this.startPageIndex >= this.length) moreBtn.style.display= "none";
}

function removeListNode(){
	let leftList = document.querySelector(".displayProductList_left");
	let rightList = document.querySelector(".displayProductList_right");
	
	while(leftList.hasChildNodes()) leftList.removeChild(leftList.firstChild);

	while(rightList.hasChildNodes()) rightList.removeChild(rightList.firstChild);

}

function templatingCategory(categoryList){
	const categoryHTML = document.querySelector("#categoryItem").innerHTML;
	
	for(i = 0; i < categoryList.length; i++){
		let resultHTML = "";
		
		resultHTML += categoryHTML
				.replace(/{categoryId}/g,categoryList[i].id)
				.replace(/{categoryName}/g,categoryList[i].name);
		
		const categoryLi = document.querySelector("#event_tab");
		categoryLi.insertAdjacentHTML("beforeend",resultHTML);
	}
}

function templatingCount(totalCount){
	let countPlace = document.querySelector(".pink");
	countPlace.innerHTML = "";
	countPlace.insertAdjacentHTML("afterbegin",totalCount+"개");
}

function templatingProduct(productList) {

	let productHTML = document.querySelector("#productItem").innerHTML;
	const limit = 2;
    
	for (i = 0; i < productList.length; i++) {

	    if(i % limit == 0){
	    	let leftResultHTML="";
	        
			leftResultHTML += productHTML
			        .replace(/{productDescription}/g,productList[i].productDescription)
			        .replace(/{productId}/g, productList[i].productId)
					.replace(/{productContent}/g, productList[i].productContent)
					.replace(/{placeName}/g, productList[i].placeName)
	                .replace(/{productImageUrl}/g, productList[i].productImageUrl)
					.replace(/{displayInfoId}/g, productList[i].displayInfoId);
	                 
			let leftLi = document.querySelector(".displayProductList_left");
            leftLi.insertAdjacentHTML("beforeend", leftResultHTML);
		         
	    }
        else {
        	let rightResultHTML="";
	        
			rightResultHTML += productHTML
	               .replace(/{productDescription}/g,productList[i].productDescription)
	               .replace(/{productId}/g, productList[i].productId)
			       .replace(/{productContent}/g, productList[i].productContent)
			       .replace(/{placeName}/g, productList[i].placeName)
	               .replace(/{productImageUrl}/g, productList[i].productImageUrl)
				   .replace(/{displayInfoId}/g, productList[i].displayInfoId);
           
			let rightLi = document.querySelector(".displayProductList_right");
           rightLi.insertAdjacentHTML("beforeend", rightResultHTML);
               
        }
    }
	
}
                                                                                                                                             
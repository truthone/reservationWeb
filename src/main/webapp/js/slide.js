var target = document.querySelector(".visual_img");
var size = target.childElementCount;
var count = 0;
var image_right = -414; 
var next;

function slide(next)
{  
    var delay = 2000;
    var now = new Date().getTime();
    
    if(next == undefined) next = now + delay;
    
    if(now > next){
    	target.style.transform = "translateX(" + (count*image_right) + "px)";
        count++;
        next = now + delay;
    }
    
    if(count >= size){
    	count = 0;
    	next= now + delay;
    }
    
    requestAnimationFrame(function(){slide(next)});
}

slide(next)
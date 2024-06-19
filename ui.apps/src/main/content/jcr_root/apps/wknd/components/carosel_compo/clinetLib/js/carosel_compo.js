document.addEventListener("DOMContentLoaded", function() {
    "use strict";

    var currentIndex = 0;
    var totalSlides = document.querySelectorAll(".carousel-item").length;
    
    <!--var totalSlides = document.querySelectorAll(".carousel-item:not(.d-none)").length;-->

     var carouselContainer = document.getElementById("myCarousel");
    var intervalId;

    console.log(totalSlides );

	 document.querySelector(".carousel-control-prev").addEventListener("click", function() {
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        updateCarousel();
        resetAutoPlay();
    });

    document.querySelector(".carousel-control-next").addEventListener("click", function() {
        currentIndex = (currentIndex + 1) % totalSlides;
        updateCarousel();
        resetAutoPlay();
    }); 


      function updateCarousel() {
        var translateValue = -currentIndex * 100 + "%";
        console.log(translateValue)
        document.querySelectorAll(".carousel-item").forEach((divContainer, index) => {
        	console.log(divContainer);
            divContainer.style.transform = "translateX(" + translateValue + ")";
        });
    }


     var indicators = document.querySelectorAll(".carousel-indicators li");

    indicators.forEach(function(indicator, index) {
        indicator.addEventListener("click", function() {
            currentIndex = index;
            updateCarousel();
            resetAutoPlay();
        });
    });




	startAutoPlay();

    function startAutoPlay() {

        intervalId = setInterval(function () {
            currentIndex = (currentIndex + 1) % totalSlides;
            updateCarousel();
        }, 2000); 

    }


     function resetAutoPlay() {
        clearInterval(intervalId);
        startAutoPlay();
    }




    function pauseCarousel(){

         clearInterval(intervalId);
    }


    carouselContainer.addEventListener("mouseenter", function () {
         pauseCarousel();

    });


    carouselContainer.addEventListener("mouseleave", function () {
        startAutoPlay();
    });



	document.querySelectorAll(".carousel-item").forEach(function(item) {

        item.addEventListener("mouseenter", function () {
            document.body.style.cursor = "pointer";
        });

        item.addEventListener("mouseleave", function () {
            document.body.style.cursor = "auto";
        });
    });




});

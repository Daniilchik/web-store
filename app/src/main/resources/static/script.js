let currentSlide = 0;
const slides = document.querySelectorAll('.slide');
let intervalId;

function showSlide(index) {
    const totalSlides = slides.length;
    if (index >= totalSlides) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = totalSlides - 1;
    } else {
        currentSlide = index;
    }

    const offset = -currentSlide * 100;
    document.querySelector('.slides').style.transform = `translateX(${offset}%)`;
}

function nextSlide() {
    showSlide(currentSlide + 1);
}

function prevSlide() {
    showSlide(currentSlide - 1);
}

function pauseSlide() {
    clearInterval(intervalId);
}

function playSlide() {
    pauseSlide();
    intervalId = setInterval(nextSlide, 3000);
}

document.addEventListener('DOMContentLoaded', () => {
    showSlide(currentSlide);
    playSlide();
});
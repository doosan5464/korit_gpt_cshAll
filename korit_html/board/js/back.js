const backButton = document.querySelector(".back-button");
backButton.onclick = () => {
    goBack();
}
function goBack() {
    location.href = "./list.html";
}
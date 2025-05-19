function createFeedback(){
    let petition = {
        ride: sessionStorage.getItem("idRide"),
        text: document.getElementById("feedback").value,
        star: document.querySelector('input[name="rating"]:checked').value
    }
    console.log(petition);
    fetch('http://localhost:8080/taxi/new-petition', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(petition)
    })
    document.getElementById("message").innerText = "Отзыв отправлен";
}
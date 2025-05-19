function createFeedback(){
    let petition = {
        ride: sessionStorage.getItem("rideId"),
        text: document.getElementById("feedback").value,
        star: document.querySelector('input[name="rating"]:checked').value
    }
    console.log(petition);
    fetch('http://localhost:8080/taxi/driver/new-petition', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(petition)
    })
    document.getElementById("message").innerText = "Отзыв отправлен";
    let id = sessionStorage.getItem("idDriver")
    let tarif = sessionStorage.getItem("tarifDriver");
    sessionStorage.clear();
    sessionStorage.setItem("idDriver", id);
    sessionStorage.setItem("tarifDriver", tarif);
}
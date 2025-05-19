let message = document.getElementById("message");
function getStatus(){

    fetch('http://localhost:8080/taxi/ride/'+sessionStorage.getItem("orderId"),{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            console.log(sessionStorage.getItem("orderId"));
            return null;
        })
        .then(result => {
            if (result!==null){
                sessionStorage.setItem("idRide", result.id);
                console.log(result.statuss.toString());
                if (result.statuss.toString()==="1"){
                    message.innerText="Водитель в пути\n" +
                        "к вам подъедет";
                    getCar(result.driver).then(str => {
                        message.innerText += " "+ str;
                    });
                }
                if (result.statuss.toString()==="2") {
                    message.innerText = "Водитель вас ожидает\n" +
                        "машина:";
                    getCar(result.driver).then(str => {
                        message.innerText += " "+ str;
                    });
                }
                if (result.statuss.toString()==="3"){
                    message.innerText="Вы в пути";
                }
                if (result.statuss.toString()==="4"){
                    window.location.href='client3.html';
                }}

        })

}

async function getCar(driver){
    return await fetch('http://localhost:8080/taxi/driver-car/'+driver,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
        })
        .then(result => {
            console.log(result.car.toString());
            return result.car.toString();
        })
}
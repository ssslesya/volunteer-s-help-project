
function setCoords(){
    let x1 = document.getElementById("addressX").value;
    let y1 = document.getElementById("addressY").value;
    if ((x1!==null)&&(y1!==null)){
        sessionStorage.setItem("x1",x1);
        sessionStorage.setItem("y1",y1);
        window.location.href='driver1.html';
    }
}
function startRide(){
    let order = document.getElementById("order").value;
    let coords = {
        "x1": sessionStorage.getItem("x1"),
        "y1": sessionStorage.getItem("y1")
    }
    fetch('http://localhost:8080/taxi/driver/get-order/'+ sessionStorage.getItem("tarifDriver"), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(coords)
    })
        .then(response => {
            if (!response.ok) {
                console.log("ошиба");
            }
            return response.json();
        })
        .then(result => {
            if(order==="1"){
                sessionStorage.setItem("orderRide", result[0].id)
            }
            if(order==="2"){
                sessionStorage.setItem("orderRide", result[1].id)
            }
            if(order==="3"){
                sessionStorage.setItem("orderRide", result[2].id)
            }
        })
    let ride ={
        orderid:sessionStorage.getItem("orderRide"),
        driver:sessionStorage.getItem("idDriver"),
        statuss:1,
    }
    fetch('http://localhost:8080/taxi/driver/create-ride',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(ride)
    })
        .then(response => {
            return response.json();
        })
        .then(result => {
            sessionStorage.setItem("rideId", result.id);
            console.log(sessionStorage.getItem("rideId"));
             window.location.href='driver2.html';
        })

}

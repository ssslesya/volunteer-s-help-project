function logIn() {
    fetch('http://localhost:8080/taxi/driver/get-driver', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: sessionStorage.getItem("id").toString()
    })
        .then(response=>{
            if (response.ok) {
                console.log(sessionStorage.getItem("id").toString());
                return response.json();
        } return null;
            })
        .then(result=>{
            if (result===null){
                window.location.href='registerDriver.html';
            }
            console.log(result.toString());
            sessionStorage.setItem("idDriver", result.id);
            sessionStorage.setItem("tarifDriver", result.tarif)
            console.log(sessionStorage.getItem("idDriver"));
            console.log(sessionStorage.getItem("tarifDriver"));
            window.location.href='driver0.html';
    }
    )
}
function reg(){
    let fullname = document.getElementById("fullname").value;
    let passport = document.getElementById("passport").value;
    let license = document.getElementById("license").value;
    let car = document.getElementById("car").value;
    let tarif = document.getElementById("tarif").value;
    let driver = {
        fullname: fullname,
        passport: passport,
        license: license,
        phone: sessionStorage.getItem("id").toString(),
        car: car,
        tarif: tarif
    }
    if(fullname!=="" && passport !=="" && license !== ""){
        fetch('http://localhost:8080/taxi/add-driver', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(driver)
        })
            .then(response => {
                if (!response.ok) {
                    console.log(fullname, passport, license,car, tarif, sessionStorage.getItem("id").toString())
                    document.getElementById("message").innerText = "Ошибка";
                    throw new Error('хз как получилась эта ошибка');
                }
                document.getElementById("message").innerText = "Успешно";
        })
    }
}
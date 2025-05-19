function register(){
    let name = document.getElementById("name").value;
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;

    let client = {
        name: name,
        id: id,
        password: password
    };
    if(name!=="" && id !=="" && password !== ""){
        fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(client)
        })
            .then(data => {
                console.log(data.status.toString())
                if(data.status.toString()==="500"){
                    document.getElementById("message").innerText = "Номер телефона занят";
                }else{
                    document.getElementById("message").innerText = "Вы успешно зарегистрировались, можете совершить вход"
                }
            })
    }
}
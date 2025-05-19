function login() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let isValid = true;
    
    email.classList.remove('error');
    password.classList.remove('error');

        if (!email.value.trim()) {
            email.classList.add('error');
            isValid = false;
        }

        if (!password.value.trim()) {
            password.classList.add('error');
            isValid = false;
        }

    let client = {
        id: email,
        password: password,

    };
    if(isValid) {
        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                 'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(client)
        })
            .then(response => {
            if (!response.ok) {
                document.getElementById("message").innerText = "Неправильный логин или пароль";
                throw new Error('Invalid login or password');
            }
            return response.json();
        })
            .then(client => {

                if (client.name==="admin"){
                    window.location.href='admin0.html';
                }else{
                    document.getElementById("message").innerText = "Успешно";
                    sessionStorage.setItem("id", client.id);
                    window.location.href='client0.html';
                }

            })
    }
    else{
        alert('Пожалуйста, заполните все поля.');
    }

}
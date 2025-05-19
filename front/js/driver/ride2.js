ymaps.ready(mapsRide2)
async function mapsRide2() {

    let response = await fetch('http://localhost:8080/taxi/driver/order/' + sessionStorage.getItem("orderRide"), {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    console.log("ок или нет ок?")
    if (response.ok) {
        console.log("ок");
        let result = await response.json();
        let myPlacemark,
            myMap = new ymaps.Map('yandexmap', {
                center: [result.addressfinalx, result.addressfinaly],
                zoom: 14
            }, {
                searchControlProvider: 'yandex#search'
            });
        myMap.events.add('click', function (e) {
            var coords = e.get('coords');
            if (myPlacemark) {
                myPlacemark.geometry.setCoordinates(coords);
            } else {
                myPlacemark = createPlacemark(coords);
                myMap.geoObjects.add(myPlacemark);
            }
        })
        let filtered = [];
        myMap.geoObjects.add(new ymaps.Placemark([result.addressfinalx, result.addressfinaly], {
            iconCaption: 'Конечная точка'
        }, {
            preset: 'islands#redIcon'
        }))
    }
    function createPlacemark(coords) {
        return new ymaps.Placemark(coords, {
            iconCaption: 'Ваше местоположение'
        }, {
            preset: 'islands#violetDotIconWithCaption',
            draggable: true
        });
    }

}
async function status4() {
    fetch('http://localhost:8080/taxi/driver/update-ride/' + sessionStorage.getItem("rideId"), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(4)
    })
        .then(response => {
            if (!response.ok) {
                console.log("ошиба");
            }
            window.location.href = 'driver5.html';
        })
}



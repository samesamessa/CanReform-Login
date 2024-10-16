document.querySelector('#submit').addEventListener('click', (e) => {
    e.preventDefault();

    const email = document.querySelector(`#email`)
    const password = document.querySelector(`#password`)

    fetch(`/api/login`, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify({
            email : email.value,
            password : password.value
        })
    })
        .then((resp) => {
            if(!resp.ok && resp.status !== 400){
                console.log(resp.status)
                throw new Error("로그인 실패" + resp.status);
            }
            return resp.json();
        })
        .then(data => {
            if (data.error) {
                alert("data 오류 발생" + data.error)
            } else {
                alert("로그인 성공")
                location.href="/"
            }
        })
        .catch((error) => {
            console.error("fetch 에러:", error); // 전체 에러 객체 출력
            alert("로그인 오류 발생: " + error.message); // 사용자에게 오류 메시지 표시
        });

})
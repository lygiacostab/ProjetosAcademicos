        let camera_button = document.querySelector("#start-camera");
        let video = document.querySelector("#video");
        let click_button = document.querySelector("#click-photo");

        let canvas = document.querySelector("#canvas");

        let stream = null;
        
        camera_button.addEventListener('click', async () => {

            stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: false });
            video.srcObject = stream;
        });
        click_button.addEventListener('click', () => {
            canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
            let image_data_url = canvas.toDataURL('image/jpeg');
            // a imagem pode ser obtida e salva a partir dessa url
            console.log(image_data_url);
            if (stream){
                const tracks = stream.getTracks();
                tracks.forEach(track => track.stop());
                video.srcObject = null;
            }
        });
        document.getElementById("envia-foto").addEventListener('click', function (e) {
            alert('Recebemos sua imagem. A avaliação poderá levar até 24hs, você receberá uma notificação pelo e-mail informado. Obrigado!');
        })

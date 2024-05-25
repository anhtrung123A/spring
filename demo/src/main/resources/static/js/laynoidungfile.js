function layNoiDung(input) {
    let file = input.files[0];

    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsText(file);
        reader.onload = (e) => {
            resolve(e.target.result);
        };
    });
}

function layBitFile(input) {
    let file = input.files[0];
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsBinaryString(file);
        reader.onload = (e) => {
            resolve(e.target.result);
        };
    });
}
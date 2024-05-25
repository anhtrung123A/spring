function convertToPrivateKey(pem) {
    return new Promise((resolve, reject) => {
        var privateKey = forge.pki.privateKeyFromPem(pem);
        resolve(privateKey);
    });
}

function convertToPublicKey(pem) {
    return new Promise((resolve, reject) => {
        var publicKey = forge.pki.publicKeyFromPem(pem);
        resolve(publicKey);
    });
}

function checkKey(privateKey, publicKey) {
    return new Promise((resolve, reject) => {
        let md = forge.md.sha256.create();
        md.update('sign this', 'utf8');
        let signature = privateKey.sign(md);
        if (publicKey.verify(md.digest().bytes(), signature)) {
            resolve(true);
        } else {
            reject(false)
        }

    });
}

function createKey() {
    var rsa = forge.pki.rsa;
    return new Promise((resolve, reject) => {
        var keypair = rsa.generateKeyPair({bits: 2048, e: 0x10001});
        resolve(keypair)
    });
    
}
const basedUrl = 'http://localhost:8001/';
const request = (data = null,method = 'GET', url = basedUrl)=>{
    if(data){
        let paramsUrl = "?"
        for(let key in data) paramsUrl += key + '=' + data[key]
        url+= paramsUrl
    }
    const xhr = new XMLHttpRequest();
    xhr.open(method,url)
    xhr.onreadystatechange = ()=>{
        if(xhr.readyState === 4 && xhr.status === 200){
                const response = xhr.response()
                console.log(response)
        }
    }
}

export {request};
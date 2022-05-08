const basedUrl = 'http://localhost:8001/';
const request = (data = null,method = 'GET', url = basedUrl)=>{
    if(data){
        //concat the parameters, form the query string url
        let paramsUrl = "?"
        for(let key in data) {
          if(data[key]) {
            paramsUrl += key + "&"
            const len = data[key].length
          for(let i = 0 ; i < len;i++){
              if(i === len-1) paramsUrl += data[key][i]
              else paramsUrl += data[key][i] + '&'
          }
          }
          else paramsUrl += key
        }
        url+= paramsUrl
    }
    const xhr = new XMLHttpRequest();
    return new Promise((resolve,reject)=>{
        xhr.onreadystatechange = ()=>{
            if(xhr.readyState !== 4) return;
            if(xhr.status === 200) resolve(xhr.responseText)
            else reject(xhr.statusText)
        }
        xhr.open(method,url)
        xhr.send()
    })
}


export {request};
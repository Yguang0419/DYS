// 存储token
export function setToken(tokenKey,token) {
    return sessionStorage.setItem(tokenKey,token);
}

// 获取token
export function getToken(tokenKey) {
    return sessionStorage.getItem(tokenKey);
}
// 移除token
export function removeToken(tokenKey) {
    if(getToken(tokenKey)) {
        return sessionStorage.removeItem(tokenKey);
    }
    return null;
}
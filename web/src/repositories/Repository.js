import axios from "axios";

export const AuthTokenStorageKey = '_BASIC_AUTHENTICATION_TOKEN_';

const LogPrefix = '[Repository]';
export class Repository {
    getRequestPromise = (method, url, param, data, contentType, responseType) => {
        const token = sessionStorage.getItem(AuthTokenStorageKey);
        const headers = {};
        if(Boolean(token)) {
            headers['X-Auth-Token'] = token;
        }
        if(contentType) {
            headers['Content-Type'] = contentType;
            console.log('headers : ', headers);
        }
        
        
        return new Promise((resolve, reject) => {
            const config = {
                method: method,
                url: url,
                headers: headers,
                params: param,
                data: data,
                responseType: responseType ? responseType : 'json',
            };
            
            // console.log(LogPrefix, 'HTTP requesting :', config);
            axios.request(config)
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    reject(error);
                });
        });
    }

    getAuthTokenFromStorage = () => {
        return sessionStorage.getItem(AuthTokenStorageKey);
    }

    setAuthTokenToStorage = (token) => {
        sessionStorage.setItem(AuthTokenStorageKey, token);
    }

    removeAuthTokenFromStorage = () => {
        sessionStorage.removeItem(AuthTokenStorageKey);
    }
}
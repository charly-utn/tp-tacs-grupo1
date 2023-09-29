import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/'

export var instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Credentials': 'true',
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})

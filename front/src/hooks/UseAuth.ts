import { useReducer } from "react"
import { LoginReducer } from "../reducers/LoginReducer"
//import Swal from "sweetalert2"

//import { useNavigate } from "react-router-dom"
import { loginUser } from "../services/UsersApiClient"
import { Credentials } from "../interfaces/Credentials"

const initialLogin = JSON.parse(localStorage.getItem('login') || '{}') || {isAuth: false, user: undefined}

//JSON.parse(localStorage.getItem('login') || '{isAuth: false, user: undefined}')

export const UseAuth = () => {
    const [login, dispatch] = useReducer(LoginReducer, initialLogin)
   // const navigate = useNavigate()

    const handlerLogin = async(credentials: Credentials) => {
      //try {
          const userLogged = await loginUser(credentials)
          console.log(`LOOOOO ${userLogged}`)
          dispatch({
              type: 'login',
              payload: userLogged
          })
          localStorage.setItem('token', userLogged.token);
          localStorage.setItem('login', JSON.stringify({
              isAuth: true,
              user: {
                userName: userLogged.userName
              }
          }))
          //navigate('/users')

      /*} catch (error) {
          Swal.fire('Error login', 'Username o password invalidos', 'error')
      }*/
      

  }

  const handlerLogout = () => {
      dispatch({
          type: 'logout',
      })
      localStorage.removeItem('login')
      //localStorage.removeItem('token');
  }

  return {
      login,
      handlerLogin,
      handlerLogout
  }
}

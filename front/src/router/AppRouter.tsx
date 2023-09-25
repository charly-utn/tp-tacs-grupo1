import { Route, Routes } from 'react-router-dom';
import { Login, Register } from '../auth' 
import { Navbar } from '../components/navbar/Navbar';
export const AppRouter = () => {
  return (
    <>
        <Navbar />

        <div className="containers">
          <Routes>
            <Route path="/*" element={<Login/>} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
          </Routes>
        </div>
    </>
  )
}

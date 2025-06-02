import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/login';
import PerfilAdmin from './components/perfilAdmin';
import Home from './components/home';
import PerfilUsuario from './components/perfilUsuario';
import Equipo from './components/equipo';

function App() {
  // Aquí obtén usuario y rol de donde tengas la info, por ejemplo del estado o contexto
  const usuarioLogueado = localStorage.getItem("usuarioLogueado");
  const rol = localStorage.getItem("rol");

  // Si hay usuario logueado, muestra las rutas protegidas
  return (
    <Router>
      <Routes>
        {/* otras rutas */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        {rol === "admin" && <Route path='/perfilAdmin' element={<PerfilAdmin usuarioLogueado={usuarioLogueado} rol={rol} /> } />}
        <Route path='/perfilAdmin' element={<Navigate to="/login"/>}/>
        {rol === "usuario" && <Route path='/perfilUsuario' element={<PerfilUsuario usuarioLogueado={usuarioLogueado} rol={rol} /> } />}
        <Route path='/perfilUsuario' element={<Navigate to="/login"/>}/>
        <Route path='/equipo' element={<Equipo />} />
      </Routes>
    </Router>
  );
}

export default App;
import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  return (
    <div className="navbar">
      <h2>Smart Village</h2>

      <div className="nav-links">
        <button onClick={() => navigate("/")}>Home</button>
        <button onClick={() => navigate("/login")}>Login</button>
      </div>
    </div>
  );
}

export default Navbar;
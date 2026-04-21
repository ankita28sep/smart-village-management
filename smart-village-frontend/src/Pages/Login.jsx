import { useState } from "react";
import { loginUser } from "../services/api";
import { useNavigate } from "react-router-dom";

function Login() {
  const [form, setForm] = useState({
  email: "",
  password: ""
});

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async () => {
  try {
    const data = await loginUser(form);

    console.log("LOGIN RESPONSE:", data);

    localStorage.setItem("token", data.token);

    navigate("/");

  } catch (error) {
    alert("Login failed");
  }
};

  return (
    <div className="container">
      <h2 className="section-title">Login</h2>

      <div className="card" style={{ maxWidth: "400px", margin: "auto" }}>
      <input
  type="email"
  name="email"
  placeholder="Email"
  onChange={handleChange}
  style={{ width: "100%", padding: "10px", marginBottom: "10px" }}
/>

<input
  type="password"
  name="password"
  placeholder="Password"
  onChange={handleChange}
  style={{ width: "100%", padding: "10px", marginBottom: "10px" }}
/>

        <button onClick={handleLogin} style={{ width: "100%", padding: "10px" }}>
          Login
        </button>
      </div>
    </div>
  );
}

export default Login;
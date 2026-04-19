import { Link } from "react-router-dom";

export default function Dashboard() {
  return (
    <div>
      <h2>Dashboard</h2>

      <br />

      <Link to="/schemes">View Schemes</Link>
    </div>
  );
}
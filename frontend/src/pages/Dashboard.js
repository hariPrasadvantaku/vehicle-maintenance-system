import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axiosConfig";

function Dashboard() {
  const navigate = useNavigate();
  const [vehicles, setVehicles] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    api.get("/vehicle")
      .then(res => setVehicles(res.data))
      .catch(err => console.log(err));

  }, [navigate]);

  return (
    <div>
      <h2>My Vehicles</h2>

      <button onClick={() => navigate("/add-vehicle")}>
        Add Vehicle
      </button>

      {vehicles.length === 0 && <p>No vehicles found</p>}

      {vehicles.map(v => (
        <div key={v.id} style={{border:"1px solid gray", padding:"10px", margin:"10px"}}>
          
          <h3>{v.vehicleName}</h3>

          <p><b>Make:</b> {v.make}</p>
          <p><b>Model:</b> {v.model}</p>
          <p><b>Registration:</b> {v.registrationNo}</p>
          <p><b>Fuel Type:</b> {v.fuelType}</p>
          <p><b>Purchase Date:</b> {v.purchaseDate}</p>
          <p><b>Insurance Expiry:</b> {v.insuranceExpiryDate}</p>

          <button onClick={() => navigate("/edit-vehicle/" + v.id)}>
            Edit
          </button>

          <button
            onClick={() => {
              api.delete("/vehicle/" + v.id).then(() => {
                setVehicles(vehicles.filter(x => x.id !== v.id));
              });
            }}
          >
            Delete
          </button>

        </div>
      ))}
    </div>
  );
}

export default Dashboard;

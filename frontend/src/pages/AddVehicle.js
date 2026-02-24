import { useState } from "react";
import { addVehicle } from "../services/vehicleService";
import { useNavigate } from "react-router-dom";

function AddVehicle() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    vehicleName: "",
    make: "",
    model: "",
    registrationNo: "",
    fuelType: "",
    purchaseDate: "",
    insuranceExpiryDate: ""
  });

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  function saveVehicle() {
    addVehicle(form)
      .then(() => navigate("/"))
      .catch(err => console.log(err));
  }

  return (
    <div>
      <h2>Add Vehicle</h2>

      <input name="vehicleName" placeholder="Name" onChange={handleChange}/>
      <input name="make" placeholder="Make" onChange={handleChange}/>
      <input name="model" placeholder="Model" onChange={handleChange}/>
      <input name="registrationNo" placeholder="Reg No" onChange={handleChange}/>
      <input name="fuelType" placeholder="Fuel" onChange={handleChange}/>
      <input type="date" name="purchaseDate" onChange={handleChange}/>
      <input type="date" name="insuranceExpiryDate" onChange={handleChange}/>

      <button onClick={saveVehicle}>Save</button>
    </div>
  );
}

export default AddVehicle;

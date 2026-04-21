function SchemeCard({ scheme }) {
  return (
    <div className="card">
      <h3>{scheme.name}</h3>
      <p>{scheme.description}</p>
      <p><b>Department:</b> {scheme.department}</p>
    </div>
  );
}

export default SchemeCard;
import React from 'react';
import useProfile from '../hooks/useProfile';

const Profile = () => {
  const { profile, loading, error } = useProfile(); // Destructuring

  // Display loading message while the data is being fetched
  if (loading) return <p>Loading profile...</p>;

  // Display error message
  if (error) return <p className="text-red-500">{error}</p>;

  // display the profile details
  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Your Profile</h1>

      <div className="mb-4">
        <strong>Name: </strong>
        <p>
          {profile.first_name} {profile.last_name}
        </p>
      </div>

      <div className="mb-4">
        <strong>Email: </strong>
        <p>{profile.email}</p>
      </div>

      <div className="mb-4">
        <strong>Title: </strong>
        <p>{profile.title}</p>
      </div>

      <div className="mb-4">
        <strong>Department: </strong>
        <p>{profile.department}</p>
      </div>
    </div>
  );
};

export default Profile;

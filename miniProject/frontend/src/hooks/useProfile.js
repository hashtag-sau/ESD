import { useState, useEffect } from 'react';
import axiosClient from '../utils/axiosClient';

const useProfile = () => {
  const [profile, setProfile] = useState(null); // Store profile data
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch profile data when the component mounts
  useEffect(() => {
    axiosClient
      .get('/detail')
      .then((response) => {
        setProfile(response.data); // On success, setting profile data
      })
      .catch((err) => {
        setError('Failed to load profile data.');
      })
      .finally(() => {
        setLoading(false);
      });
  }, []); // Empty dependency array, so this effect only runs once

  return { profile, loading, error };
};

export default useProfile;

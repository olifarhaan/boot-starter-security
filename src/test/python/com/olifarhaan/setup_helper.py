from api_helpers import (
    post_request,
    get_request,
    get_unique_id,
    authenticate_and_retrieve_token,
    get_headers_with_auth,
)
from datetime import datetime, timedelta
import random
full_names = [
    "Ali Farhan",
    "John Doe",
    "Jane Doe",
    "Alice Johnson",
    "Bob Smith",
    "Charlie Brown",
    "Diana Green",
    "Ethan White",
    "Fiona Black",  
]

def setup_user(data={}):
    name = random.choice(full_names)
    user_data = {
        "fullName": f"{name}",
        "email": f"{get_unique_id(name)}@example.com",
        "password": "securepassword"
    }
    user_data.update(data)
    response = post_request("auth/signup", user_data)
    retrieved_data = response.json()
    user_id = retrieved_data["id"]
    user_token = authenticate_and_retrieve_token(retrieved_data["email"], user_data["password"])
    return user_id, user_token, retrieved_data
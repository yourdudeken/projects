from twilio.rest import Client

# Twilio credentials
account_sid = 'your_account_sid'
auth_token = 'your_auth_token'
client = Client(account_sid, auth_token)

# WhatsApp numbers
from_whatsapp_number = 'whatsapp:+14155238886'  # This is your Twilio sandbox number
to_whatsapp_number = 'whatsapp:+1233567890'    # Replace with the recipient's WhatsApp number

# Sending a message
message = client.messages.create(
    body='Hello, this is a message from Twilio!',
    from_=from_whatsapp_number,
    to=to_whatsapp_number
)

print(f'Message sent! SID: {message.sid}')
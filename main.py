from PIL import Image
import io
import tensorflow as tf
import numpy as np
from flask import Flask, request,jsonify

model = tf.keras.models.load_model("model.h5")
jenis = ['Abyssinian', 'American Bobtail', 'American Curl', 'American Shorthair', 'American Wirehair', 'Balinese', 'Bengal', 'Birman', 'Bombay', 'British Shorthair', 'Burmese', 'Burmilla', 'Chartreux', 'Chausie', 'Cornish Rex', 'Cymric', 'Devon Rex', 'Domestic Longhair', 'Domestic Mediumhair', 'Egyptian Mau', 'Exotic Shorthair', 'Havana', 'Himalayan', 'Japanese Bobtail', 'Korat', 'LaPerm', 'Maine Coon', 'Manx', 'Munchkin', 'Nebelung', 'Norwegian Forest', 'Ocicat', 'Oriental Long Hair', 'Oriental Shorthair', 'Persian', 'Pixiebob', 'Ragamuffin', 'Ragdoll', 'Russian Blue', 'Scottish Fold', 'Selkirk Rex', 'Siamese', 'Siberian', 'Singapura', 'Snowshoe', 'Somali', 'Sphynx', 'Tonkinese', 'Turkish Angora', 'Turkish Van']

def transform_image(pillow_image):
    data = np.asarray(pillow_image)
    data = data/255.0
    data = data[np.newaxis, ...] #[1,x,y,3]
    data = np.expand_dims(data, axis=-1)
    data = np.repeat(data, 3, axis=-1)
    data = tf.image.resize(data, [180,180])
    
    return data

def predict(x):
    pred = model(x)
    pred0 = pred[0]
    label0 = np.argmax(pred0)
    return label0

app = Flask(__name__)

@app.route("/", methods=["POST"])
def index():
    if request.method == "POST":     
        file = request.files.get('file')
        if file is None or file.filename == "":
            return jsonify({"error": "no file"})
    try:
        image_bytes = file.read()
        pillow_img = Image.open(io.BytesIO(image_bytes)).convert('L')
        tensor = transform_image(pillow_img)
        prediction = int(predict(tensor))
        data = {"prediction" : jenis[prediction]}
        return jsonify(data)
    
    except Exception as e:
        return jsonify({"error" : str(400)})
    
    return "OK"

if __name__ == "__main__":
    app.run(debug=True)

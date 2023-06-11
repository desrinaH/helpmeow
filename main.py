from PIL import Image
import io
import tensorflow as tf
import numpy as np
from flask import Flask, request,jsonify

model = tf.keras.models.load_model("model.h5")

def transform_image(pillow_image):
    data = np.asarray(pillow_image)
    data = data/255.0
    data = data[np.newaxis, ...] #[1,x,y,3]
    data = np.expand_dims(data, axis=-1)
    data = np.repeat(data, 3, axis=-1)
    data = tf.image.resize(data, [200,200])
    
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
        prediction = predict(tensor)
        data = {"prediction" : int(prediction)}
        return jsonify(data)
    
    except Exception as e:
        return jsonify({"error" : str(e)})
    
    return "OK"

if __name__ == "__main__":
    app.run(debug=True)
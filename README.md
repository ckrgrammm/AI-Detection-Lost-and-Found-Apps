# About FYPs

The development of a camera-based object detection and reporting feature for an Android app involved integrating sophisticated technologies like OpenCV and TensorFlow Lite. This feature's primary purpose is to empower users to report found items by utilizing the device's camera for object detection. Once an object is detected, the app prompts the user to confirm if the detected item is the one they intend to report. Upon confirmation, the app captures an image of the object and displays it within the user interface.

The implementation process encompassed several key components. Firstly, the CameraActivity was developed to handle camera operations using OpenCV and object detection using TensorFlow Lite. When an object is detected, the activity presents a confirmation dialog to the user. If the user confirms, the app captures the image of the object, saves it temporarily, and returns the object name and image file name to the calling activity.

The objectDetectorClass plays a crucial role in loading and utilizing a TensorFlow Lite model for object detection. This class processes camera frames, detects objects, and maintains the name of the most confidently detected object. This approach ensures that the users are presented with the most likely object they intend to report.

Additionally, a specific fragment, such as AddMaterialFragment, was developed to facilitate user interactions for reporting found items. This fragment includes a camera button to launch CameraActivity and handles the results returned from it, such as the detected object name and the image file. The fragment allows users to view the captured image, auto-fills the object name in the report form, and lets users add additional details before submitting the report.

XML layout adjustments were made to incorporate necessary UI elements, like image display and detail input fields. The use of Material Design components was emphasized to enhance the user interface and user experience. Handling permissions and activity results was another critical aspect of the development process. The app requests and manages camera permissions and handles the results from image selection and object detection activities efficiently.

Throughout the development, challenges like camera integration and object detection accuracy were encountered. These challenges were addressed through rigorous testing and fine-tuning of the object detection algorithm.

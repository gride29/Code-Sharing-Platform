<html>
    <head>
        <title>Create</title>
    </head>
    <body>
        <form>
            <textarea id="code_snippet" style="width: 1000px; height: 500px;">// write your code here</textarea>
            <div id="timeGroup" style="margin-top: 1rem;">
                <label for="time_restriction">Time restriction</label>
                <input type="text" id="time_restriction">
            </div>
            <div id="viewsGroup" style="margin-top: 1rem">
                <label for="view_restriction">View restriction</label>
                <input type="text" id="view_restriction">
            </div>
            <div style="margin-top: 1rem">
                <button id="send_snippet" type="submit" onclick="send()">Submit</button>
            </div>
        </form>
    </body>
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "views": document.getElementById("view_restriction").value,
                "time": document.getElementById("time_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status == 200) {
                alert("Success!");
            }
        }
    </script>
</html>
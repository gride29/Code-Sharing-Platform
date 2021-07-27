<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/default.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list latest as code>
    <span id="load_date">${code.date}</span>
    <pre id="code_snippet">
                <code class="language-java">
                    ${code.code}
                </code>
    </pre>
</#list>
</body>
</html>

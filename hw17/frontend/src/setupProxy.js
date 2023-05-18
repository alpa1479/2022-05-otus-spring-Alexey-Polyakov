const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/api',
        createProxyMiddleware({
            // can also be changed via flag:
            // target: 'http://localhost:8080',
            target: 'http://backend:8080',
            changeOrigin: true,
        })
    );
};
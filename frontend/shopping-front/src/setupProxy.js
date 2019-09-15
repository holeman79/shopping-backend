const proxy = require('http-proxy-middleware');

module.exports = function(app) {
    const proxyUrlPatterns = ['/oauth2/authorization/**', '/login/oauth2/**', '/api/**'];

    proxyUrlPatterns.forEach(each => {
        app.use(
            proxy(each, {
                target: 'http://localhost:5050',
                secure: false,
                ws: true,
                logLevel: 'debug'
            })
        );
    });
};
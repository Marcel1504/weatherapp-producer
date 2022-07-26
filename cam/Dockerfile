FROM arm64v8/alpine:latest

RUN apk add findutils

COPY ./cam.sh /usr/local/bin/cam.sh
RUN chmod +x /usr/local/bin/cam.sh

RUN echo '* * * * * /usr/local/bin/cam.sh' > /etc/crontabs/root

CMD ["crond", "-f", "-d", "8"]
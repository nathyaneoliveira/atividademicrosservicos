# Configuração de segurança dos serviços

Os segredos e as credenciais não têm valores padrão nos arquivos de configuração. Defina-os no ambiente de execução ou em um gerenciador de segredos antes de iniciar os serviços:

| Variável | Usada por |
|---|---|
| `DB_USERNAME`, `DB_PASSWORD` | auth-service, evento-service e os dois projetos inscricao-service |
| `RABBITMQ_USERNAME`, `RABBITMQ_PASSWORD` | inscricao-service e notification-service |
| `JWT_SECRET` | auth-service, evento-service e os dois projetos inscricao-service |

`JWT_SECRET` deve ser uma chave aleatória e imprevisível com pelo menos 32 bytes UTF-8. Use o mesmo valor em todos os serviços que emitem ou validam tokens. Guarde-o fora do código e dos arquivos versionados; ao rotacioná-lo, os tokens existentes deixam de ser válidos.

As rotas autenticadas esperam um token no cabeçalho `Authorization: Bearer <token>`. Login e cadastro públicos permanecem liberados; as demais rotas seguem as regras de roles definidas em cada serviço.

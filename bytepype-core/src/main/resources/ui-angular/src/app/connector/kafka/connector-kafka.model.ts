import { Connector } from '../connector.model';

export interface KafkaConnector extends Connector {
  // Details tab
  bootstrapServers: string;
  useSasl: boolean;
  useSsl: boolean;

  // SASL tab
  saslMechanism?: string;
  saslJaasConfig?: string;
  username?: string;
  password?: string;
  keytab?: string;
  principal?: string;
  tokenEndpoint?: string;
  clientId?: string;
  clientSecret?: string;

  // SSL tab
  sslTruststore?: string;
  sslTruststorePassword?: string;
  sslKeystore?: string;
  sslKeystorePassword?: string;
  sslKeyPassword?: string;
  sslEnabledProtocols?: string;
  sslProtocol?: string;
}

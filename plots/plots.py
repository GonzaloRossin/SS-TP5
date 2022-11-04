import matplotlib.pylab as plt
import pandas as pd

def QvsT():
    df = pd.read_json('QvsTime.json')
    plt.plot(df['particleCount'], df['50.0'], label='50.0')
    plt.legend()
    plt.xlabel("Particulas caidas ", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()

def parcticlesvsT():
    df = pd.read_json('ParticlesvsTime.json')

    plt.plot(df['time'], df['5.0'], label='5')
    plt.plot(df['time'], df['10.0'], label='10')
    plt.plot(df['time'], df['15.0'], label='15')
    plt.plot(df['time'], df['20.0'], label='20')
    plt.plot(df['time'], df['30.0'], label='30')
    plt.plot(df['time'], df['50.0'], label='50')
    plt.legend()
    plt.xlabel("Tiempo [S] ", fontsize=16)
    plt.ylabel("Partículas", fontsize=16)
    plt.show()

'''''df = pd.read_json('positionOverTime.json')
df2 = pd.read_json('errorsOverDelta.json')

delta = df2['delta']
errorVerlet = df2['errorVerlet']
errorBeeman = df2['errorBeeman']
errorGCP = df2['errorGCP']

pAnalytical = df['pAnalytical']
pBeeman = df['pBeeman']
pGCP = df['pGCP']
pVerlet = df['pVerlet']
x = df['time']'''

QvsT()
parcticlesvsT()

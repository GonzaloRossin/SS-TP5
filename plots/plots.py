import matplotlib.pylab as plt
import pandas as pd

def QvsT():
    df = pd.read_json('QvsTime5.0.json')
    plt.plot(df['time'], df['Q'])
    plt.legend()
    plt.xlabel("Tiempo [S] ", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()

def parcticlesvsT():
    df = pd.read_json('ParticlesvsTime5.0.json')

    plt.plot(df['time'], df['particles'])
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

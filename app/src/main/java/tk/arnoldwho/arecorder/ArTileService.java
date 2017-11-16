package tk.arnoldwho.arecorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;
import android.app.AlertDialog;

/**
 * Created by arnold on 2017/11/10.
 */

public class ArTileService extends TileService{

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        changeTileState(getQsTile().getState());
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();
        startActivity(new Intent("Activity2"));
        //changeTileState(0);
    }

    private void changeTileState(int newState) {
        getQsTile().setIcon(Icon.createWithResource(ArTileService.this, newState == Tile.STATE_INACTIVE ? R.drawable.tile_disabled : R.drawable.tile_enabled));
        getQsTile().setState(newState);
        getQsTile().updateTile();
    }
}
